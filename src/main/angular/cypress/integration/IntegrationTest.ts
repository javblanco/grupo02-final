describe("Conexión Test", () => {
    it("Crear conexión", () => {
        cy.visit("/conexiones")
        cy.get(".btn").contains("Crear Conexión").click()
        cy.url().should("include", "/conexiones/alta")
        cy.get("#nombre").type("Cypress")
        cy.get("#lenguaje").type("TypeScript")
        cy.get("#tipoServicio").type("Prueba de Cypress")
        cy.get(".btn").contains("Crear conexión").click()
        cy.url().should("include", "/conexiones")
    })

    it("Editar conexión", () => {
        cy.visit("/conexiones")
        cy.get(':nth-child(2) > :nth-child(4) > a > .btn').contains("Editar").click()
        cy.url().should("include", "/conexiones/detalle/1")
        cy.get("#nombre").clear()
        cy.get("#nombre").type("EDITADO")
        cy.get(".btn").contains("Editar conexión").click()
        cy.url().should("include", "/conexiones")
    })

    it("Eliminar conexión", () => {
        cy.visit("/conexiones")
        cy.get(':nth-child(2) > :nth-child(4) > .btn-danger').contains("Eliminar").click()
        
    })
})