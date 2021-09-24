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

describe("Flujo Test", () => {
    it("Crear Flujo", () => {
        cy.visit("/flujos")
        cy.get(".btn").contains("Crear Flujo").click()
        cy.url().should("include", "/flujos/alta")
        cy.get("#nombre").type("Test")
        cy.get("#tiempoInicio").type("2017-06-01T08:30")
        cy.get("#tiempoFin").type("2017-06-01T08:30")
        cy.get("#estado").check()
        cy.get("#instancia").get("select").select("1: Object")
        cy.get(".btn").contains("Crear flujos").click()
        cy.url().should("include", "/flujos")
    })

    it("Editar Flujo", () => {
        cy.visit("/flujos")
        cy.get(':nth-child(2) > :nth-child(7) > a > .btn').contains("Editar").click()
        cy.url().should("include", "/flujos/detalle/1")
        cy.get("#nombre").clear()
        cy.get("#nombre").type("EDITADO")
        cy.get(".btn").contains("Editar flujos").click()
        cy.url().should("include", "/flujos")
    })

    it("Eliminar Flujo", () => {
        cy.visit("/flujos")
        cy.get(':nth-child(2) > :nth-child(7) > .btn-danger').contains("Eliminar").click()
        
    })
})

describe("Instancia Test", () => {
    it("Crear Instancia", () => {
        cy.visit("/instancias")
        cy.get(".btn").contains("Crear Instancia").click()
        cy.url().should("include", "/instancias/alta")
        cy.get("#nombreInstancia").type("Test")
        cy.get(".btn").contains("Crear instancia").click()
        cy.url().should("include", "/instancias")
    })

    it("Editar Insancia", () => {
        cy.visit("/instancias")
        cy.get(':nth-child(2) > :nth-child(3) > a > .btn').contains("Editar").click()
        cy.url().should("include", "/instancias/detalle/1")
        cy.get("#nombreInstancia").clear()
        cy.get("#nombreInstancia").type("EDITADO")
        cy.get(".btn").contains("Editar instancia").click()
        cy.url().should("include", "/instancias")
    })

    it("Eliminar Instancia", () => {
        cy.visit("/instancias")
        cy.get(':nth-child(2) > :nth-child(3) > .btn-danger').contains("Eliminar").click()
        
    })
})