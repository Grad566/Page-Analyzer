@import hexlet.code.dto.sites.SitesPage
@import hexlet.code.paths.Paths
@param SitesPage page

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
                @for(var site : page.getSites())
                    <tr>
                        <th scope="row">${site.getId()}</th>
                        <th><a href="${Paths.urlsIdPath(site.getId())}">${site.getName()}</a></th>
                    </tr>
            @endfor
            </tbody>
        </table>
    `
)