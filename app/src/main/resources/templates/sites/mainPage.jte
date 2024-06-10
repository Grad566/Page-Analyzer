@template.layout.page(
    content = @`
        <div class="container text-center">
            <div class="row">
                <div class="col">
                    <h1>Анализатор страниц</h1>
                </div>
            </div>
            <form action="/urls" method="post">
                <div class="mb-3">
                    <label class="form-label">Бесплатно проверяйте сайты на SEO пригодность</label>
                    <div class="col-auto">
                        <input name="url" type="text" class="form-control form-control-lg" placeholder="Ссылка">
                    </div>
                    <div class="form-text">Пример: https://www.example.com</div>
                    <div class="col-auto">
                        <button class="btn btn-primary col-auto" type="submit">Проверить</button>
                    </div>
                </div>
            </form>
        </div>
    `
)