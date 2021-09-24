describe("Test de navegacion", () => {
    it("De Inicio a Conexion", () => {
        cy.visit("/")
        cy.get(":nth-child(3) > a").click()
        cy.url().should("include", "/conexiones")
    })

    it("De Inicio a Flujo", () => {
        cy.visit("/")
        cy.get(":nth-child(4) > a").click()
        cy.url().should("include", "/flujo")
    })

    it("De Inicio a Instancias", () => {
        cy.visit("/")
        cy.get(":nth-child(5) > a").click()
        cy.url().should("include", "/instancia")
    })

    it("De Conexión a Inicio", () => {
        cy.visit("/conexiones")
        cy.get(':nth-child(2) > a > .btn').click()
        cy.url().should("include", "/")
    })
})