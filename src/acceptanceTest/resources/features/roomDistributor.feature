#language: pt
Funcionalidade: Distribuição de assentos

  Cenário: Distribui quartos de acordo com gênero, faixa etária, igreja e bairro
    Dadas as fichas cadastrais
      | id | idade | genero    | paroquia                                     | capela               | bairro              |
      | 1  | 14    | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Matriz               | Santo Antônio       |
      | 2  | 17    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Santo Antônio       |
      | 3  | 19    | Masculino |                                              | Matriz               | Santo Antônio       |
      | 4  | 25    | Feminino  |                                              | Santo Antônio        | Enseada             |
      | 5  | 15    | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro |                      | Embaré              |
      | 6  | 16    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro |                      | Embaré              |
      | 7  | 25    | Feminino  | Santo Amaro                                  |                      | Castelo             |
      | 8  | 20    | Feminino  | Santo Amaro                                  |                      | Castelo             |
      | 9  | 18    | Masculino |                                              |                      | Barra Funda         |
      | 10 | 20    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Barreira            |
      | 11 | 25    | Masculino |                                              | Santo Antônio        | Canto do Forte      |
      | 12 | 27    | Feminino  |                                              | Santo Antônio        | Morrinhos           |
      | 13 | 30    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Barreira            |
      | 14 | 27    | Masculino |                                              |                      | Parque Continental  |
      | 15 | 24    | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Macuco              |
      | 16 | 15    | Masculino |                                              | Santo Antônio        | Macuco              |
      | 17 | 15    | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | São Paulo            | Vila Edna           |
      | 18 | 16    | Feminino  |                                              |                      | Barreira            |
      | 19 | 21    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio        | Morrinhos           |
      | 20 | 18    | Feminino  | Santo Amaro                                  |                      | Castelo             |
      | 21 | 19    | Masculino |                                              | Santo Antônio        | Morrinhos           |
      | 22 | 19    | Masculino |                                              | Santo Antônio        | Morrinhos           |
      | 23 | 17    | Masculino |                                              |                      | Santa Rosa          |
      | 24 | 16    | Masculino |                                              |                      | Barra Funda         |
      | 25 | 17    | Masculino |                                              | Santa rosa de lima   | Jardim dos Pássaros |
      | 26 | 17    | Feminino  | Santa rosa de lima                           |                      | Jardim dos Pássaros |
      | 27 | 19    | Masculino |                                              |                      | Santa Rosa          |
      | 28 | 20    | Masculino |                                              |                      | Santa Rosa          |
      | 29 | 16    | Feminino  |                                              | São José de Anchieta | Bela Vista          |
      | 30 | 16    | Masculino |                                              |                      | Barra Funda         |
      | 31 | 25    | Masculino |                                              | Matriz               | Embaré              |
      | 32 | 27    | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Matriz               | Santo Antônio       |
      | 33 | 24    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro |                      | Boqueirão           |
      | 34 | 15    | Feminino  |                                              |                      | Boqueirão           |
      | 35 | 15    | Feminino  |                                              |                      | Boqueirão           |
      | 36 | 24    | Feminino  |                                              | Santo Antônio        | Enseada             |
      | 37 | 27    | Feminino  | Santa rosa de lima                           |                      | Jardim dos Pássaros |
      | 38 | 27    | Masculino |                                              | Santo Antônio        | Santo Antônio       |
      | 39 | 26    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Matriz               | Santo Antônio       |
    E o número do quarto feminino com a ordenação
      | quarto | ordem |
      | 54     | C     |
    E o número do quarto masculino com a ordenação
      | quarto | ordem |
      | 96     | D     |
    Quando ocorrer o processamento dos dados
    Entao a divisão dos quartos deverá ser
      | id | quarto |
      | 2  | 54     |
      | 8  | 54     |
      | 37 | 54     |
      | 6  | 55     |
      | 10 | 55     |
      | 7  | 55     |
      | 18 | 56     |
      | 19 | 56     |
      | 39 | 56     |
      | 20 | 57     |
      | 33 | 57     |
      | 4  | 57     |
      | 26 | 58     |
      | 36 | 58     |
      | 12 | 58     |
      | 29 | 59     |
      | 34 | 59     |
      | 13 | 59     |
      | 35 | 60     |
      | 1  | 96     |
      | 15 | 96     |
      | 14 | 96     |
      | 5  | 95     |
      | 3  | 95     |
      | 11 | 95     |
      | 9  | 94     |
      | 21 | 94     |
      | 31 | 94     |
      | 16 | 93     |
      | 27 | 93     |
      | 32 | 93     |
      | 17 | 92     |
      | 22 | 92     |
      | 28 | 92     |
      | 23 | 91     |
      | 24 | 91     |
      | 38 | 91     |
      | 25 | 90     |
      | 30 | 90     |


  Cenário: Distribui quartos entre 3 homens e 3 mulheres
    Dadas as fichas cadastrais
      | id | idade | genero    | paroquia                                     | capela        | bairro         |
      | 1  | 14    | Feminino  | Santo Amaro                                  |               | Castelo        |
      | 2  | 15    | Masculino |                                              |               | Barra Funda    |
      | 3  | 19    | Feminino  | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
      | 4  | 27    | Masculino |                                              | Santo Antônio | Canto do Forte |
      | 5  | 20    | Feminino  |                                              | Santo Antônio | Morrinhos      |
      | 6  | 19    | Masculino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
    E o número do quarto feminino com a ordenação
      | quarto | ordem |
      | 54     | C     |
    E o número do quarto masculino com a ordenação
      | quarto | ordem |
      | 96     | D     |
    Quando ocorrer o processamento dos dados
    Entao a divisão dos quartos deverá ser
      | id | quarto |
      | 1  | 54     |
      | 3  | 54     |
      | 5  | 54     |
      | 2  | 96     |
      | 6  | 96     |
      | 4  | 96     |

  Cenário: Distribui quartos entre pessoas do mesmo gênero
    Dadas as fichas cadastrais
      | id | idade | genero   | paroquia                                     | capela        | bairro         |
      | 1  | 24    | Feminino | Santo Amaro                                  | Santo Antônio | Castelo        |
      | 2  | 26    | Feminino |                                              |               | Barra Funda    |
      | 3  | 16    | Feminino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
      | 4  | 29    | Feminino |                                              | Santo Antônio | Canto do Forte |
      | 5  | 21    | Feminino |                                              |               | Morrinhos      |
      | 6  | 15    | Feminino | Matriz Nossa Senhora de Fátima e Santo Amaro | Santo Antônio | Barreira       |
    E o número do quarto feminino com a ordenação
      | quarto | ordem |
      | 54     | C     |
    E o número do quarto masculino com a ordenação
      | quarto | ordem |
      | 96     | D     |
    Quando ocorrer o processamento dos dados
    Entao a divisão dos quartos deverá ser
      | id | quarto |
      | 3  | 54     |
      | 5  | 54     |
      | 2  | 54     |
      | 6  | 55     |
      | 1  | 55     |
      | 4  | 55     |