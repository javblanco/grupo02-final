import { Instancia } from "./instancia";

export class Flujo {

  id: number | undefined;
  nombre: string | undefined;
  tiempoInicio: Date | undefined;
  tiempoFin: Date | undefined;
  instancia: Instancia | undefined;
  estado: boolean | undefined;

  constructor() {}
  
}