@import hexlet.code.dto.sites.MainPage
@param MainPage page

@template.layout.page(
    content = @`
        @if(page.getFlash() != null)
            <div class="alert alert-warning" role="alert">
                ${page.getFlash()}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Закрыть"></button>
            </div>
        @endif
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