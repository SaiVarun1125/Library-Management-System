define(function (require) {

    var env = require('zrender/core/env');

    function SaveAsImage (mdl) {
        this.mdl = mdl;
    }

    SaveAsImage.defaultOption = {
        show: true,
        icon: 'M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
        title: '保存为图片',
        type: 'png',
        // Default use option.backgroundColor
        // backgroundColor: '#fff',
        name: '',
        excludeComponents: ['toolbox'],
        pixelRatio: 1,
        lang: ['右键另存为图片']
    };

    SaveAsImage.prototype.unusable = !env.canvasSupported;

    var proto = SaveAsImage.prototype;

    proto.onclick = function (ecModel, api) {
        var mdl = this.mdl;
        var title = mdl.get('name') || ecModel.get('title.0.text') || 'echarts';
        var $a = document.createElement('a');
        var type = mdl.get('type', true) || 'png';
        $a.download = title + '.' + type;
        $a.target = '_blank';
        var url = api.getConnectedDataURL({
            type: type,
            backgroundColor: mdl.get('backgroundColor', true)
                || ecModel.get('backgroundColor') || '#fff',
            excludeComponents: mdl.get('excludeComponents'),
            pixelRatio: mdl.get('pixelRatio')
        });
        $a.href = url;
        // Chrome and Firefox
        if (typeof MouseEvent === 'function') {
            var evt = new MouseEvent('click', {
                view: window,
                bubbles: true,
                cancelable: false
            });
            $a.dispatchEvent(evt);
        }
        // IE
        else {
            var lang = mdl.get('lang');
            var html = ''
                + '<body style="margin:0;">'
                + '<img src="' + url + '" style="max-width:100%;" title="' + ((lang && lang[0]) || '') + '" />'
                + '</body>';
            var tab = window.open();
            tab.document.write(html);
        }
    };

    require('../featureManager').register(
        'saveAsImage', SaveAsImage
    );

    return SaveAsImage;
});