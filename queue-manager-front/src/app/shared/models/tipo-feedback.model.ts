export class TipoFeedback {
  constructor(public id: string, public name: string) {}
}

export interface IFeedBack {
  type: string;
  message: string;
}
