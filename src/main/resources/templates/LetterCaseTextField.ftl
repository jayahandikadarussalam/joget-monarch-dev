<div class="form-cell" ${elementMetaData!}>
    <#if !(includeMetaData!) && element.properties.style! != "" >
        <script type="text/javascript" src="${request.contextPath}/plugin/org.joget.apps.form.lib.TextField/js/jquery.numberFormatting.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                    $('.textfield_${element.properties.elementUniqueKey!}').numberFormatting({
                    format : '${element.properties.style!}',
                    numOfDecimal : '${element.properties.numOfDecimal!}',
                    useThousandSeparator : '${element.properties.useThousandSeparator!}',
                    prefix : '${element.properties.prefix!}',
                    postfix : '${element.properties.postfix!}'
                });
            });
        </script>
    </#if>

    <#if !(includeMetaData!) && (element.properties.lettercase!'') != ''>
        <script type="text/javascript">
            $(document).ready(function() {
                var $input = $('.textfield_${element.properties.elementUniqueKey!}');
                var lettercase = '${element.properties.lettercase!}';

                $input.on('input', function() {
                    var val = this.value;

                    if (lettercase === 'uppercase') {
                        this.value = val.toUpperCase();
                    } else if (lettercase === 'lowercase') {
                        this.value = val.toLowerCase();
                    } else if (lettercase === 'capitalize') {
                        this.value = val.toLowerCase().replace(/\b\w/g, function(char) {
                            return char.toUpperCase();
                        });
                    }
                });
            });
        </script>
    </#if>

    <label field-tooltip="${elementParamName!}" class="label" for="${elementParamName!}">${element.properties.label} <span class="form-cell-validator">${decoration}</span><#if error??> <span class="form-error-message">${error}</span></#if></label>
    <#if (element.properties.readonly! == 'true' && element.properties.readonlyLabel! == 'true') >
        <div class="form-cell-value"><span>${valueLabel!?html}</span></div>
        <input id="${elementParamName!}_${element.properties.elementUniqueKey!}" name="${elementParamName!}" class="textfield_${element.properties.elementUniqueKey!}" type="hidden" value="${value!?html}" />
    <#else>
        <input id="${elementParamName!}_${element.properties.elementUniqueKey!}" name="${elementParamName!}" class="textfield_${element.properties.elementUniqueKey!}" type="text" placeholder="${element.properties.placeholder!?html}" size="${element.properties.size!}" value="${value!?html}" maxlength="${element.properties.maxlength!}" <#if error??>class="form-error-cell"</#if> <#if element.properties.readonly! == 'true'>readonly</#if> />
    </#if>
</div>
