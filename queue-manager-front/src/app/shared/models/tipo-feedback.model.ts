export class TipoFeedback {
  constructor(public id: string, public name: string) {}
}

export interface IFeedBack {
  type: string;
  message: string;
}

export interface IFeedBackResponse {
  id: string;
  type: string;
  message: string;
  status: IStatus;
}

interface IStatus {
  valor: number;
}
