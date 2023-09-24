export class TipoFeedback {
  constructor(public id: string, public name: string) {}
}

export enum EnumTipoFeedback {
  ELOGIO = 'ELOGIO',
  CRITICA = 'CRITICA',
  SUGESTAO = 'SUGESTAO',
}

export interface IFeedBack {
  type: string;
  message: string;
}

export interface IFeedBackResponse {
  MessageId: string;
  type: string;
  message: string;
  status: string;
}
