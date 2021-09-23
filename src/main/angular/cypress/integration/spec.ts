describe("Navigation test", () => {
    it("La pagina conecta", () => {
        cy.visit('http://localhost:4200')
    })

    it("Entra a conexiones", () => {
        cy.get(':nth-child(3) > a').click()
    })

    it("Vuelve a la principal y entra a Flujo", () => {
        cy.contains("Inicio").click()
        cy.get(':nth-child(4) > a').click()
    })

    it("Vuelve a la principal y entra a Instancias", () => {
        cy.contains("Inicio").click()
        cy.get(':nth-child(5) > a').click()
    })
})

describe("Crear una conexión", () => {
    it("Entrar a conexiones", () => {
        cy.visit("http://localhost:4200")
        cy.get(':nth-child(3) > a').click()
    })

    it("Entrar al formulario de crear", () => {
        cy.get(':nth-child(3) > a > .btn').click()
    })

    it("Introducir datos y crear la conexión", () => {
        cy.get("#nombre").type("Cypress")
        cy.get("#lenguaje").type("TypeScript")
        cy.get("#tipoServicio").type("Prueba de Cypress")
        cy.get(':nth-child(3) > .btn').click()
    })
})

describe("Editar la conexión Cypress", () => {
    it("Buscar y entar en la conexión", () => {
        cy.get("tr")
        cy.get("td")
        cy.get("button[id=3]").click()

        
    })

    it("Editar la conexión", () => {
        cy.get("#nombre").clear()
        cy.get("#nombre").type("EDITADO")
        cy.get(':nth-child(3) > .btn').click()
    })
})

describe("Eliminar una conexión", () => {
    it("Eliminar conexión", () => {
        cy.get("button[id=3-delete]").click()
    })
})