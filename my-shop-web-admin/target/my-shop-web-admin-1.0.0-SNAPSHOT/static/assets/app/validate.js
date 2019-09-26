/**
 * jQuery 有效性验证
 * @agoni
 */
var Validate = function () {

    /**
     * 增加自定义验证规则
     */
    var handlerInitCustomerValidate = function () {

        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    /**
     * 初始化 jQuery Validation
     * @param formId
     */
    var handlerInitValidate = function (formId) {

        $("#" + formId).validate({
            errorElement: 'span',
            errorClass: 'help-block',
            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    };

    return {
        /**
         * 初始化校验规则
         */
        init: function () {
            handlerInitCustomerValidate();
        },

        /**
         * 表单验证
         * @param formId
         */
        validateForm: function (formId) {
            handlerInitValidate(formId);
        }
    }
}();

/**
 * 调用这个js，会执行这个函数
 */
$(function () {
    Validate.init();
    Validate.validateForm("inputForm");
});