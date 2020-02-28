GrowlNotification.setGlobalOptions({
                buttons: {
                    action: {
                        text: '确定'
                    },
                    cancel: {
                        text: '关闭'
                    }
                }
            });

            function getOptions() {
                var position = 'bottom-right';
                var closeTimeout = 5000;
                var animation = 'slide';
                var showButtons = true;
                var showProgressBar = true;
                var image = 'img/default.png';
                var animationOptions = {
                    open: animation + '-in',
                    close: animation + '-out'
                };

                if (animation === 'none') {
                    animationOptions = {
                        open: false,
                        close: false
                    };
                }

                return options = {
                    position: position,
                    closeTimeout: closeTimeout,
                    image : image,
                    closeWith: ['click'],
                    animation: animationOptions,
                    showButtons: showButtons,
                    buttons: {
                        action: {
                            callback: function (notification) {
                                console.log('action button');
                            }
                        }
                    },
                    showProgress: showProgressBar
                };
            }
