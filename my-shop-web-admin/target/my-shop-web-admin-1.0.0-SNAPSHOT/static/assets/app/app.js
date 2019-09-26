//函数对象，通用的
var App = function () {
    /**
     * 私有属性
     * @type {*|jQuery.fn.init|jQuery|HTMLElement}
     * @private
     */

    //iChick
    var _masterCheckBox;
    var _checkBox;

    //用于存放ID的数组
    var _idArray;

    //默认的 Dropzone 参数
    var defaultDropzoneOpts = {
        url: "",// 文件提交地址
        paramName: "dropFile",// 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };
    /**
     * 私有方法
     */
    var handlerInItCheckbox = function () {
        // 激活 iCheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
        //获取控制端 checkBox
        _masterCheckBox = $('input[type = "checkbox"].minimal.iCheck_master');
        //获取全部 CheckBox集合
        _checkBox = $('input[type = "checkbox"].minimal');
    };

    /**
     * CheckBox 全选功能
     */
    var handlerCheckBoxAll = function () {
        //全选，取消全选
        _masterCheckBox.on("ifClicked", function (even) {
            //true 未选中
            if (even.target.checked) {
                //全不选
                _checkBox.iCheck("uncheck");
            }
            //false 选中
            else {
                //全选
                _checkBox.iCheck("check");
            }
        });
    };


    /**
     * 删除单笔记录
     * @param url 删除链接
     * @param id 需要删除数据的 ID
     */
    var handlerDeleteSingle = function (url, id, msg) {
        // 可选参数
        if (!msg) msg = null;

        // 将 ID 放入数组中，以便和批量删除通用
        _idArray = new Array();
        _idArray.push(id);

        $("#modal-message").html(msg == null ? "您确定删除数据项吗？" : msg);
        $("#modal-default").modal("show");
        // 绑定删除事件
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });
    };


    /**
     * 批量删除
     */
    var handlerDeleteMulti = function (url) {
        _idArray = new Array();
        //遍历 checkBox
        _checkBox.each(function () {
            //获取属性id的值
            var _id = $(this).attr("id");
            //将选中的id数组中
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        //将弹出模态框呼出来，无论选不选都要弹出，所以放在外面
        $("#modal-default").modal("show");

        //没有选中选项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }
        //选中了，确认是否删除
        else {
            $("#modal-message").html("您确定删除数据项吗？");
        }
        /**
         * 给确定按钮绑定事件，点击会调用函数
         */
        $("#ModalOk").bind("click", function () {
            handlerDeleteData(url);
        });
    };


    /**
     * AJAX 异步删除
     * @param url
     */
    var handlerDeleteData = function (url) {
        $("#modal-default").modal("hide");

        if (_idArray.length > 0) {
            // AJAX 异步删除操作
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids" : _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        // 请求成功后，无论是成功或是失败都需要弹出模态框进行提示，所以这里需要先解绑原来的 click 事件
                        $("#btnModalOk").unbind("click");

                        // 请求成功
                        if (data.status === 200) {
                            // 刷新页面
                            $("#btnModalOk").bind("click", function () {
                                window.location.reload();
                            });
                        }

                        // 请求失败
                        else {
                            // 确定按钮的事件改为隐藏模态框
                            $("#btnModalOk").bind("click", function () {
                                $("#modal-default").modal("hide");
                            });
                        }

                        // 因为无论如何都需要提示信息，所以这里的模态框是必须调用的
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            }, 500)
        }
    };

    /**
     * 初始化 DataTables
     * @param url
     * @param columns
     * @returns {jQuery}
     */

    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url,
            },
            "columns": columns,
            //国际化
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            //回调函数，表加载完之后，重新激活一次样式
            "drawCallback": function (settings) {
                handlerInItCheckbox();
                handlerCheckBoxAll();
            }
        });
        return _dataTable;
    };


    /**
     * 初始化 zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url, autoParam, callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                <!-- 获取上一个的pid后自动往后台传参数-->
                autoParam: autoParam
            }
        };

        $.fn.zTree.init($("#myTree"), setting);

        $("#ModalOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();

            //未选择
            if (nodes.length == 0) {
                alert(" 请先选择一个节点");
            }
            //已选择
            else {
                callback(nodes);
            }
        });
    };

    /**
     * 初始化 Dropzone
     * @param opts
     */
    var handlerInitDropzone = function (opts) {
        // Disable auto discover for all elements:
        Dropzone.autoDiscover = false;
        //继承
        var options = $.extend(defaultDropzoneOpts,opts);
        new Dropzone(options.id, options);
    };

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
        //这里是通过 ajax 请求 html 的方式将 jsp 装载到模态框中
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 将私有方法暴露出来
     */
    return {
        /**
         * 初始化
         */
        init: function () {
            handlerInItCheckbox();
            handlerCheckBoxAll();
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        /**
         * 删除单笔数据
         * @param url
         */
        deleteSingle: function(url, id, msg) {
            handlerDeleteSingle(url, id, msg);
        },

        /**
         * 初始化 DataTbles
         * @param url
         * @param columns
         * @returns {*|jQuery}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 初始化 zTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function (url, autoParam, callback) {
            handlerInitZTree(url, autoParam, callback);
        },

        /**
         * 显示详情
         * @param url
         */
        showDetail: function (url) {
            handlerShowDetail(url);
        },

        /**
         * 初始化 Dropzone
         * @param opts
         */
        initDropzone : function (opts) {
            handlerInitDropzone(opts);
        }
    }
}();

$(document).ready(function () {
    App.init();
});