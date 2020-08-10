## Game of Life
Java implementation of Conway's Game of Life using OOP best practices. The game can be played with different rules and be displayed in a GUI or console. It was made for college years ago and later I'll refactor this README and do some clean up in root files.

Descrição:
Este programa foi criado como uma resolucao da atividade proposta na disciplina de Programacao Orientada a Objetos da Universidade de Brasilia. Trata-se da implementacao do jogo Game of Life empregando alguns padroes de projeto de software para corrigir falhas num design previamente especificado.

Soluções propostas:
Nesta solucao, as falhas que optamos por corrigir foram:
     - Separacao de Responsabilidades de maneira mais adequada (empregando o padrao arquitetural MVC)
     - Implementacao do padrao de projeto Strategy para suportar diferentes variacoes do jogo Game of Life e permitir que essas variacoes sejam empregadas em tempo de execucao
     - Implementacao do padrao de projeto Observer, tanto para as views, que refletirao o conteudo da model a medida que esta vai sendo modificada pela controller, quanto para as Statistics, que deve ser responsavel por produzir uma mensagem contendo o numero de celulas que foram mortas ou ressuscitadas na model, para exibicao num componente de apresentacao.
     - Implementacao da Interface Grafica utilizando a API padrao do Java, Swing, possibilitando a coexistencia desta com a apresentacao em Shell.

Funcionamento:
Para utilizar o programa, basta compilar o codigo utilizando um Ambiente de Desenvolvimento Integrado (Eclipse, Netbeans, etc). O programa entao solicita o numero de linhas e de colunas do tabuleiro Game of Life a ser criado, e entao o tipo de apresentacao que o usuario deseja utilizar (Interface Grafica ou Console).
