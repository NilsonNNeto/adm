#language: pt
Funcionalidade: Distribuição de assentos

  Cenário: Distribui assentos de acordo com altura, gênero, igreja e bairro
    Dadas as fichas cadastrais
      | id | altura | genero    | paroquia                                     | capela               | bairro              |
      | 1  | 1.72   | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Matriz               | Santo Antônio       |
      | 2  | 1.72   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Santo Antônio       |
      | 3  | 1.72   | Masculino |                                              | Matriz               | Santo Antônio       |
      | 4  | 1.8    | Feminino  |                                              | Santo Antônio        | Enseada             |
      | 5  | 1.56   | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro |                      | Embaré              |
      | 6  | 1.56   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro |                      | Embaré              |
      | 7  | 1.48   | Feminino  | Santo Amaro                                  |                      | Castelo             |
      | 8  | 1.48   | Feminino  | Santo Amaro                                  |                      | Castelo             |
      | 9  | 1.63   | Masculino |                                              |                      | Barra Funda         |
      | 10 | 1.36   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Barreira            |
      | 11 | 1.91   | Masculino |                                              | Santo Antônio        | Canto do Forte      |
      | 12 | 2.01   | Feminino  |                                              | Santo Antônio        | Morrinhos           |
      | 13 | 1.36   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Barreira            |
      | 14 | 1.77   | Masculino |                                              |                      | Parque Continental  |
      | 15 | 1.59   | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Macuco              |
      | 16 | 1.59   | Masculino |                                              | Santo Antônio        | Macuco              |
      | 17 | 1.62   | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | São Paulo            | Vila Edna           |
      | 18 | 1.36   | Feminino  |                                              |                      | Barreira            |
      | 19 | 2.01   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Morrinhos           |
      | 20 | 1.48   | Feminino  | Santo Amaro                                  |                      | Castelo             |
      | 21 | 1.91   | Masculino |                                              | Santo Antônio        | Morrinhos           |
      | 22 | 1.91   | Masculino |                                              | Santo Antônio        | Morrinhos           |
      | 23 | 1.43   | Masculino |                                              |                      | Santa Rosa          |
      | 24 | 1.63   | Masculino |                                              |                      | Barra Funda         |
      | 25 | 1.76   | Masculino |                                              | Santa rosa de lima   | Jardim dos Pássaros |
      | 26 | 1.76   | Feminino  | Santa rosa de lima                           |                      | Jardim dos Pássaros |
      | 27 | 1.43   | Masculino |                                              |                      | Santa Rosa          |
      | 28 | 1.43   | Masculino |                                              |                      | Santa Rosa          |
      | 29 | 1.62   | Feminino  |                                              | São José de Anchieta | Bela Vista          |
      | 30 | 1.63   | Masculino |                                              |                      | Barra Funda         |
      | 31 | 1.56   | Masculino |                                              | Matriz               | Embaré              |
      | 32 | 1.72   | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Matriz               | Santo Antônio       |
      | 33 | 1.55   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro |                      | Boqueirão           |
      | 34 | 1.55   | Feminino  |                                              |                      | Boqueirão           |
      | 35 | 1.55   | Feminino  |                                              |                      | Boqueirão           |
      | 36 | 1.8    | Feminino  |                                              | Santo Antônio        | Enseada             |
      | 37 | 1.76   | Feminino  | Santa rosa de lima                           |                      | Jardim dos Pássaros |
      | 38 | 1.72   | Masculino |                                              | Santo Antônio        | Santo Antônio       |
      | 39 | 1.62   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Matriz               | Santo Antônio       |
    Quando ocorrer o processamento dos dados
    Entao a ordem das fichas deverá ser
      | id |
      | 23 |
      | 10 |
      | 27 |
      | 13 |
      | 28 |
      | 7  |
      | 5  |
      | 18 |
      | 31 |
      | 8  |
      | 15 |
      | 20 |
      | 16 |
      | 33 |
      | 17 |
      | 34 |
      | 1  |
      | 35 |
      | 3  |
      | 6  |
      | 9  |
      | 29 |
      | 24 |
      | 39 |
      | 30 |
      | 2  |
      | 32 |
      | 26 |
      | 38 |
      | 37 |
      | 14 |
      | 4  |
      | 25 |
      | 36 |
      | 11 |
      | 12 |
      | 21 |
      | 19 |
      | 22 |

  Cenário: Distribui assentos entre 3 homens e 3 mulher
    Dadas as fichas cadastrais
      | id | altura | genero    | paroquia                                     | capela        | bairro         |
      | 1  | 1.48   | Feminino  | Santo Amaro                                  |               | Castelo        |
      | 2  | 1.63   | Masculino |                                              |               | Barra Funda    |
      | 3  | 1.36   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
      | 4  | 1.91   | Masculino |                                              | Santo Antônio | Canto do Forte |
      | 5  | 2.01   | Feminino  |                                              | Santo Antônio | Morrinhos      |
      | 6  | 1.36   | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
    Quando ocorrer o processamento dos dados
    Entao a ordem das fichas deverá ser
      | id |
      | 3  |
      | 2  |
      | 1  |
      | 6  |
      | 5  |
      | 4  |

  Cenário: Distribui assentos entre 5 homens e 1 mulher
    Dadas as fichas cadastrais
      | id | altura | genero    | paroquia                                     | capela        | bairro         |
      | 1  | 1.48   | Masculino | Santo Amaro                                  |               | Castelo        |
      | 2  | 1.63   | Masculino |                                              |               | Barra Funda    |
      | 3  | 1.36   | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
      | 4  | 1.91   | Masculino |                                              | Santo Antônio | Canto do Forte |
      | 5  | 2.01   | Masculino |                                              | Santo Antônio | Morrinhos      |
      | 6  | 1.36   | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
    Quando ocorrer o processamento dos dados
    Entao a ordem das fichas deverá ser
      | id |
      | 3  |
      | 6  |
      | 1  |
      | 2  |
      | 4  |
      | 5  |

  Cenário: Distribui assentos entre pessoas do mesmo gênero
    Dadas as fichas cadastrais
      | id | altura | genero   | paroquia                                     | capela        | bairro         |
      | 1  | 1.48   | Feminino | Santo Amaro                                  |               | Castelo        |
      | 2  | 1.63   | Feminino |                                              |               | Barra Funda    |
      | 3  | 1.36   | Feminino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
      | 4  | 1.91   | Feminino |                                              | Santo Antônio | Canto do Forte |
      | 5  | 2.01   | Feminino |                                              | Santo Antônio | Morrinhos      |
      | 6  | 1.36   | Feminino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
    Quando ocorrer o processamento dos dados
    Entao a ordem das fichas deverá ser
      | id |
      | 3  |
      | 1  |
      | 6  |
      | 2  |
      | 4  |
      | 5  |
