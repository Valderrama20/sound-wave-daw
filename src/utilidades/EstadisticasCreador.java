package utilidades;

import modelo.artistas.Creador;
import modelo.contenido.Podcast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EstadisticasCreador {

    // Atributos
    private Creador creador;
    private int totalEpisodios;
    private int totalReproducciones;
    private double promedioReproducciones;
    private int totalSuscriptores;
    private int totalLikes;
    private int duracionTotalSegundos;
    private Podcast episodioMasPopular;
    private HashMap<Integer, Integer> episodiosPorTemporada;

    // Constructor
    public EstadisticasCreador(Creador creador) {
        this.creador = creador;
        this.episodiosPorTemporada = new HashMap<>();
        calcularEstadisticas();
    }

    // Métodos privados
    private void calcularEstadisticas() {
        if (creador == null) return;

        ArrayList<Podcast> episodios = null;
        try {
            episodios = creador.getEpisodios();
        } catch (Exception e) {
            // En caso de que getEpisodios falle (por ejemplo si la lista es null internamente)
            episodios = new ArrayList<>();
        }

        if (episodios != null) {
            this.totalEpisodios = episodios.size();
            
            for (Podcast p : episodios) {
                this.totalReproducciones += p.getReproducciones();
                this.totalLikes += p.getLikes();
                this.duracionTotalSegundos += p.getDuracionSegundos();
                
                // Episodio más popular
                if (episodioMasPopular == null || p.getReproducciones() > episodioMasPopular.getReproducciones()) {
                    episodioMasPopular = p;
                }

                // Episodios por temporada
                int temp = p.getTemporada();
                episodiosPorTemporada.put(temp, episodiosPorTemporada.getOrDefault(temp, 0) + 1);
            }
        }

        if (totalEpisodios > 0) {
            this.promedioReproducciones = (double) totalReproducciones / totalEpisodios;
        } else {
            this.promedioReproducciones = 0.0;
        }

        this.totalSuscriptores = creador.getSuscriptores();
    }

    private String formatearDuracion(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segs);
    }

    // Métodos públicos
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reporte de Estadísticas: ").append(creador.getNombreCanal()).append("\n");
        sb.append("========================================\n");
        sb.append("Total Episodios: ").append(totalEpisodios).append("\n");
        sb.append("Total Reproducciones: ").append(totalReproducciones).append("\n");
        sb.append("Promedio Reproducciones: ").append(String.format("%.2f", promedioReproducciones)).append("\n");
        sb.append("Total Suscriptores: ").append(totalSuscriptores).append("\n");
        sb.append("Total Likes: ").append(totalLikes).append("\n");
        sb.append("Engagement: ").append(String.format("%.2f", calcularEngagement())).append("%\n");
        sb.append("Duración Total Contenido: ").append(formatearDuracion(duracionTotalSegundos)).append("\n");
        sb.append("Episodio Más Popular: ").append(episodioMasPopular != null ? episodioMasPopular.getTitulo() : "N/A").append("\n");
        sb.append("Crecimiento Mensual Estimado: ").append(estimarCrecimientoMensual()).append(" nuevos suscriptores\n");
        sb.append("Desglose por Temporada:\n");
        for (Map.Entry<Integer, Integer> entry : episodiosPorTemporada.entrySet()) {
            sb.append("  Temporada ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" episodios\n");
        }
        return sb.toString();
    }

    public double calcularEngagement() {
        if (totalReproducciones == 0) return 0.0;
        // Engagement rate = (Likes / Views) * 100
        return ((double) totalLikes / totalReproducciones) * 100;
    }

    public int estimarCrecimientoMensual() {
        // Lógica de estimación: 5% de suscriptores actuales + 1 por cada 1000 reproducciones totales
        // Esto es una heurística simple.
        int crecimientoBase = (int) (totalSuscriptores * 0.05);
        int crecimientoPorVistas = totalReproducciones / 1000;
        return crecimientoBase + crecimientoPorVistas;
    }

    // Getters
    public Creador getCreador() {
        return creador;
    }

    public int getTotalEpisodios() {
        return totalEpisodios;
    }

    public int getTotalReproducciones() {
        return totalReproducciones;
    }

    public double getPromedioReproducciones() {
        return promedioReproducciones;
    }

    public int getTotalSuscriptores() {
        return totalSuscriptores;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getDuracionTotalSegundos() {
        return duracionTotalSegundos;
    }

    public Podcast getEpisodioMasPopular() {
        return episodioMasPopular;
    }

    public HashMap<Integer, Integer> getEpisodiosPorTemporada() {
        return new HashMap<>(episodiosPorTemporada); // Copia defensiva
    }

    // Overrides
    @Override
    public String toString() {
        return "EstadisticasCreador{" +
                "creador=" + (creador != null ? creador.getNombreCanal() : "null") +
                ", totalEpisodios=" + totalEpisodios +
                ", totalReproducciones=" + totalReproducciones +
                ", promedioReproducciones=" + promedioReproducciones +
                ", totalSuscriptores=" + totalSuscriptores +
                ", totalLikes=" + totalLikes +
                ", duracionTotalSegundos=" + duracionTotalSegundos +
                ", episodioMasPopular=" + (episodioMasPopular != null ? episodioMasPopular.getTitulo() : "null") +
                ", episodiosPorTemporada=" + episodiosPorTemporada +
                '}';
    }
}
