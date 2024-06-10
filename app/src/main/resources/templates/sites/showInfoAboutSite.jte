@import hexlet.code.dto.sites.SitePage
@param SitePage page


@template.layout.page(
    content = @`
        <h1>Сайт: ${page.getSite().getName()}</h1>
        <table class="table table-primary table-striped">
            <tbody>
                <tr>
                    <th>ID</th>
                    <th>${page.getSite().getId()}</th>
                </tr>
                <tr>
                    <th>Имя</th>
                    <th>${page.getSite().getName()}</th>
                </tr>
                <tr>
                    <th>Дата создания</th>
                    <th>${page.getSite().getCreatedAt()}</th>
                </tr>
            </tbody>
        </table>
    `
)