@import hexlet.code.dto.sites.SitesPage
@param SitesPage page

@template.layout.page(
    content = @`
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
                        <th>${site.getName()}</th>
                    </tr>
            @endfor
            </tbody>
        </table>
    `
)