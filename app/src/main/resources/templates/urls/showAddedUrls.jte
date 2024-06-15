@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.paths.Paths
@param UrlsPage page

@template.layout.page(
    content = @`
        @if(page.getFlash() != null)
            <div class="alert alert-primary" role="alert">
                ${page.getFlash()}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Закрыть"></button>
            </div>
        @endif
        <h1>Сайты</h1>
        <table class="table table-primary table-striped">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Имя сайта</th>
                </tr>
            </thead>
            <tbody>
                @for(var url : page.getUrls())
                    <tr>
                        <th scope="row">${url.getId()}</th>
                        <th><a href="${Paths.urlsIdPath(url.getId())}">${url.getName()}</a></th>
                    </tr>
            @endfor
            </tbody>
        </table>
    `
)