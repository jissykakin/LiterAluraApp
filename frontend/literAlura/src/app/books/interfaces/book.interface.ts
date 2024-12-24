export interface Book {
  Id:            number;
  title:         string;
  subjects:      string;
  language:      Language;
  mediaType:     MediaType;
  downloadCount: number;
  imageBook:     null | string;
  viewBook:      string;
  authors:       string[];
}

export enum Language {
  Aleman = "ALEMAN",
  Español = "ESPAÑOL",
  Frances = "FRANCES",
  Ingles = "INGLES",
}

export enum MediaType {
  Sound = "Sound",
  Text = "Text",
}


export interface Lang {
  value: string;
  lang: string;
}
