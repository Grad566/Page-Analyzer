@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.dto.urlChecks.UrlCheckPage
@param UrlCheckPage checksPage
@param UrlPage page


@template.layout.page(
    content = @`
        @if(page.getFlash() != null)
            <div class="alert alert-warning" role="alert">
                ${page.getFlash()}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Закрыть"></button>
            </div>
        @endif
        <h1>Сайт: ${page.getUrl().getName()}</h1>
        <table class="table table-primary table-striped">
            <tbody>
                <tr>
                    <th>ID</th>
                    <th>${page.getUrl().getId()}</th>
                </tr>
                <tr>
                    <th>Имя</th>
                    <th>${page.getUrl().getName()}</th>
                </tr>
                <tr>
                    <th>Дата создания</th>
                    <th>${page.getUrl().getCreatedAt()}</th>
                </tr>
            </tbody>
        </table>
        <h1>Проверки</h1>
        <form action="/urls/${page.getUrl().getId()}/checks" method="post">
            <button type="submit">Запустить проверку</button>
        </form>
        <table class="table table-primary table-striped">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Код ответа</th>
                    <th scope="col">title</th>
                    <th scope="col">h1</th>
                    <th scope="col">description</th>
                    <th scope="col">Дата проверки</th>
                </tr>
            </thead>
            @if(checksPage.getUrlChecks() != null)
                <tbody>
                    @for(var check : checksPage.getUrlChecks())
                        <tr>
                            <th scope="row">${check.getId()}</th>
                            <th><a>${check.getStatusCode()}</a></th>
                            <th><a>${check.getTitle()}</a></th>
                            <th><a>${check.getH1()}</a></th>
                            <th><a>${check.getDescription()}</a></th>
                            <th><a>${check.getCreatedAt()}</a></th>
                        </tr>
                    @endfor
                </tbody>
            @endif
        </table>
    `
)