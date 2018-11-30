package zool.action;

import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import org.apache.http.util.TextUtils;
import zool.entity.Translate;
import zool.utils.http.BuildingURL;
import zool.utils.http.HttpUtil;
import zool.utils.string.SeparateString;

import java.awt.*;

/**
 * @author : zoolye
 * @date : 2018-11-29 10:50
 * @describe : 为所选单词翻译
 */
public class TranslateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }

        //获取当前选中的单词
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();

        if (TextUtils.isEmpty(selectedText)) {
            return;
        }

        //分隔单词
        String separateString = SeparateString.separateString(selectedText);

        //构建url
        String url = BuildingURL.buildingURL(separateString);

        //获取翻译内容json格式
        String josn = HttpUtil.getJosnByInternet(url);

        //将获取到的json数据转成Translate对象
        String s = jsonToTranslate(josn);

        //显示弹窗
        showPopupBalloon(mEditor, s);

    }

    /**
     * 将json数据转成Translate对象
     *
     * @param json
     * @return
     */
    private String jsonToTranslate(String json) {

        JSONObject jsonObject = JSONObject.parseObject(json);
        Translate translate = jsonObject.toJavaObject(Translate.class);
        return translate.toString();
    }

    //显示气球弹窗弹窗
    private void showPopupBalloon(final Editor mEditor, final String result) {
        ApplicationManager.getApplication().invokeLater(() -> {
            JBPopupFactory factory = JBPopupFactory.getInstance();
            factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186),
                    new Color(73, 117, 73)), null)
                    .setFadeoutTime(50000)
                    .createBalloon()
                    .show(factory.guessBestPopupLocation(mEditor), Balloon.Position.below);
        });
    }
}