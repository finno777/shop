(function () {
    const App = {
        addProduct() {
            const btn = document.querySelectorAll('.js-product');
            const form = document.querySelector('.form-popup');
            const body = {};

            form.addEventListener('click', function (e) {
                if (e.target !== form) {
                    return;
                }

                form.classList.remove('opened');
            });

            btn.forEach(btnItem => {
                btnItem.addEventListener('click', function () {
                    form.classList.add('opened');

                    form.querySelector('button').addEventListener('click', () => {
                        const request = new XMLHttpRequest();
                        request.open('POST', 'http://localhost:8080/addProduct');
                        request.setRequestHeader('Content-Type', 'application/json');

                        body.name = form.querySelectorAll('input')[0].value;
                        body.price = form.querySelectorAll('input')[1].value;

                        if (btnItem.classList.contains('_edit')) {
                            body.productId = btnItem.closest('.catalog__item').id;
                        }

                        request.onreadystatechange = function () {
                            if (this.readyState === 4) {
                                location.reload();
                            }
                        };

                        request.send(JSON.stringify(body));
                    });
                });
            });
        },

        removeProduct() {
            const removeBtn = document.querySelectorAll('.js-remove-product');

            removeBtn.forEach(btn => {
                btn.addEventListener('click', function () {
                    console.log(1);
                    const request = new XMLHttpRequest();
                    request.open('DELETE', 'http://localhost:8080/deleteProduct?id=' + this.dataset.id);
                    request.setRequestHeader('Content-Type', 'application/json');

                    request.onreadystatechange = function () {
                        if (this.readyState === 4) {
                            location.reload();
                        }
                    };

                    request.send();
                });
            });
        },

        init() {
            this.addProduct();
            this.removeProduct();
        }
    };

    App.init();
})();