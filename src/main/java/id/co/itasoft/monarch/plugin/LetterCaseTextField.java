package id.co.itasoft.monarch.plugin;

import org.joget.apps.app.service.AppUtil;
import org.joget.apps.form.lib.TextField;
import org.joget.apps.form.model.FormData;
import org.joget.apps.form.service.FormUtil;
import org.joget.commons.util.SecurityUtil;
import org.joget.commons.util.StringUtil;

import java.util.Map;

public class LetterCaseTextField extends TextField {
    private String pluginName = "Letter Case Text Field";

    @Override
    public String getName() {
        return pluginName;
    }

    @Override
    public String getVersion() {
        return "8.0.0";
    }

    @Override
    public String getDescription() {
        return pluginName;
    }

    @Override
    public String renderTemplate(FormData formData, Map dataModel) {
        // set value
        String value = FormUtil.getElementPropertyValue(this, formData);

        value = SecurityUtil.decrypt(value);

        if (FormUtil.isReadonly(this, formData) && "true".equalsIgnoreCase(getPropertyString("readonlyLabel"))) {
            String valueLabel = value;
            if (!getPropertyString("style").isEmpty() && "true".equalsIgnoreCase(getPropertyString("storeNumeric"))) {
                valueLabel = StringUtil.numberFormat(value, getPropertyString("style"), getPropertyString("prefix"), getPropertyString("postfix"), "true".equalsIgnoreCase(getPropertyString("useThousandSeparator")), getPropertyString("numOfDecimal"));
            }
            dataModel.put("valueLabel", valueLabel);
        }

        dataModel.put("element", this);
        dataModel.put("value", value);

        return FormUtil.generateElementHtml(this, formData, "LetterCaseTextField.ftl", dataModel);
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getFormBuilderTemplate() {
        return "<label class='label'>Text Field Letter Case</label>";
    }

    @Override
    public String getLabel() {
        return pluginName;
    }

    @Override
    public String getPropertyOptions() {
        String encryption = "";
        if (SecurityUtil.getDataEncryption() != null) {
            encryption = ",{name : 'encryption', label : '@@form.textfield.encryption@@', type : 'checkbox', value : 'false', ";
            encryption += "options : [{value : 'true', label : '' }]}";
        }

        return AppUtil.readPluginResource(getClass().getName(), "/properties/form/LetterCaseTextField.json", new Object[]{encryption}, true, "messages/form/LetterCaseTextField");
    }

    @Override
    public String getFormBuilderCategory() {
        return "<span><i class=\"fas fa-crown\"></i> Monarch</span>";
    }

    @Override
    public int getFormBuilderPosition() {
        return 500;
    }

    @Override
    public String getFormBuilderIcon() {
        return "<i class=\"fas fa-text-height\"></i>";
    }
}
