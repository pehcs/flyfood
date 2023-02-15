package ufrpe.flyfood;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlyFoodSolucaoDto {
    private String melhorRota;
    private Long custo;
    private int qtdPontos;
    private int permutacoes;
}
