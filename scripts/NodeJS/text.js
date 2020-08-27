//富文本初始化
    var editor = KindEditor.create('#', {
        width: '700px',
        height: '500px  ',
        items: [
            'source', '|', 'undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
            'table', 'hr', 'emoticons', 'pagebreak',
            'anchor', 'link', 'unlink'
        ],
        filePostName: "file",
        uploadJson: Feng.ctxPath + "/common/kindeditorUploadFile",
        resizeType: 1,
        allowPreviewEmoticons: true,
        allowImageUpload: true
    });
