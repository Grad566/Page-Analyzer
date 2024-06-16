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
    <div class=".container-lg .mt-5">
        <h3>Сайты</h3>
        <table class="table table-bordered table-hover .mt-3">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Имя сайта</th>
                    <th scope="col">Последняя проверка</th>
                    <th scope="col">Код ответа</th>
                </tr>
            </thead>
            <tbody>
                @for(var url : page.getUrls())
                    <tr>
                        <th scope="row">${url.getId()}</th>
                        <th><a href="${Paths.urlsIdPath(url.getId())}">${url.getName()}</a></th>
                        @if(page.getLastChecks().containsKey(url.getId()))
                            <td>${page.getLastChecks().get(url.getId()).getCreatedAt()}</td>
                            <td>${page.getLastChecks().get(url.getId()).getStatusCode()}</td>
                        @endif
                    </tr>
            @endfor
            </tbody>
        </table>
    </div>
    `
)