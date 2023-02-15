package ufrpe.flyfood;

import java.lang.*;
import java.util.*;

public class FlyFood {
    public HashMap<String, int[]> pontos = new HashMap<>();
    private String menorRota;
    private int permutacoes_cont = 0;
    private Long menorCusto = null;

    public FlyFood(String[][] caminho) {
        for (int linha = 0; linha < caminho.length; linha++) {
            for (int coluna = 0; coluna < caminho.length; coluna++) {
                if (!caminho[linha][coluna].equals("0")) pontos.put(caminho[linha][coluna], new int[]{linha, coluna});
            }
        }
    }

    public FlyFoodSolucaoDto buscarMelhorCaminho() {

        List<String> pointsKeys = new ArrayList<>();
        for (
                String key : pontos.keySet()) {
            if (!key.equals("R")) {
                pointsKeys.add(key);
            }
        }

        this.permutacoes(pointsKeys, "R");
        return FlyFoodSolucaoDto.builder()
                .melhorRota(menorRota)
                .custo(menorCusto)
                .qtdPontos(pointsKeys.size() + 1)
                .permutacoes(permutacoes_cont).build();
    }

    private void permutacoes(List<String> pointsList, String prefix) {
        if (pointsList.size() == 0) {
            permutacoes_cont += 1;
            String rota = prefix + "R";
            this.menorCaminho(rota);
            return;
        }

        for (int i = 0; i < pointsList.size(); i++) {
            String point = prefix + pointsList.get(i);
            if (menorCusto != null) {
                boolean descartaCaminhoRuim = descartaCaminhoRuim(point);
                if (descartaCaminhoRuim) {
                    return;
                }
            }
            String removido = pointsList.remove(i);
            permutacoes(pointsList, point);
            pointsList.add(i, removido);
        }

    }

    private boolean descartaCaminhoRuim(String rota) {
        int custo = 0;
        String[] pontoLista = rota.split("");
        for (int p = 0; p < pontoLista.length - 1; p++) {
            int[] currentPoint = pontos.get(
                    pontoLista[p]
            );
            int[] nextPoint = pontos.get(
                    pontoLista[p + 1]
            );
            int coluna = Math.abs(currentPoint[0] - nextPoint[0]);
            int linha = Math.abs(currentPoint[1] - nextPoint[1]);
            int total = coluna + linha;
            custo += total;
            if (custo > this.menorCusto) {
                return true;
            }
        }
        return false;
    }

    private void menorCaminho(String rota) {
        int custo = 0;
        String[] pontoLista = rota.split("");
        for (int p = 0; p < pontoLista.length - 1; p++) {
            int[] currentPoint = pontos.get(
                    pontoLista[p]
            );
            int[] nextPoint = pontos.get(
                    pontoLista[p + 1]
            );
            int coluna = Math.abs(currentPoint[0] - nextPoint[0]);
            int linha = Math.abs(currentPoint[1] - nextPoint[1]);
            int total = coluna + linha;
            custo += total;
            if (this.menorCusto != null) {
                if (custo > this.menorCusto) {
                    return;
                }
            }
        }
        if (this.menorCusto == null) {
            this.menorCusto = (long) custo;
            this.menorRota = rota;
        }
        if (custo < this.menorCusto) {
            this.menorCusto = (long) custo;
            this.menorRota = rota;
        }

    }
}
