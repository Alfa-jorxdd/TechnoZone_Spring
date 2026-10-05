package com.proyecto.ventas_online.domain.factory;

import com.proyecto.ventas_online.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class DefaultProductsFactory {

    public static final String LAPTOPS = "Laptops";
    public static final String PERIPHERALS = "Peripherals";
    public static final String COMPONENTS = "Components";
    public static final String AUDIO = "Audio";

    private DefaultProductsFactory() {}

    public static List<Product> createDefaults(Map<String, UUID> categories) {
        return List.of(
                // Laptops
                p("Laptop Gamer Titan 15", "Ryzen 7, 16 GB RAM, RTX 4060, SSD 512 GB", "4599.00", categories.get(LAPTOPS), "laptop-gamer_01.jpg", 8),
                p("Laptop Gamer Falcon 17", "Core i7, 32 GB RAM, RTX 4070, SSD 1 TB", "6199.00", categories.get(LAPTOPS), "laptop-gamer_02.jpg", 5),
                p("Laptop Gamer Nova 14", "Ryzen 5, 16 GB RAM, RTX 3050, SSD 512 GB", "3299.00", categories.get(LAPTOPS), "laptop-gamer_03.jpg", 12),

                // Componentes
                p("Tarjeta Gráfica RTX 4060 8GB", "GDDR6, ray tracing, DLSS 3", "1699.00", categories.get(COMPONENTS), "tarjeta-grafica_01.jpg", 10),
                p("Memoria RAM DDR5 16GB", "5600 MHz, disipador de aluminio", "289.00", categories.get(COMPONENTS), "memoria-ram_01.jpg", 40),
                p("Disco Duro 2TB", "HDD 7200 RPM, SATA III", "259.00", categories.get(COMPONENTS), "disco-duro_01.jpg", 25),
                p("Fuente de Poder 750W 80+ Gold", "Modular, ventilador silencioso", "399.00", categories.get(COMPONENTS), "fuente-de-poder_01.jpg", 15),
                p("Gabinete Gamer RGB", "Vidrio templado, 4 ventiladores RGB", "329.00", categories.get(COMPONENTS), "gabinete-gamer_01.jpg", 9),
                p("CPU Gamer Ryzen 5", "PC armada: Ryzen 5, 16 GB RAM, SSD 512 GB", "2899.00", categories.get(COMPONENTS), "cpu_01.jpg", 6),

                // Periféricos
                p("Teclado Mecánico Gamer", "Switches rojos, retroiluminado RGB", "189.00", categories.get(PERIPHERALS), "teclado-gamer_01.jpg", 30),
                p("Mouse Gamer 16000 DPI", "Sensor óptico, 7 botones programables", "119.00", categories.get(PERIPHERALS), "mouse-gamer_01.jpg", 35),
                p("Mousepad XXL", "Superficie de tela, base antideslizante", "59.00", categories.get(PERIPHERALS), "mousepad_01.jpg", 50),
                p("Monitor Gamer 27\" 165Hz", "QHD, panel IPS, 1 ms", "1099.00", categories.get(PERIPHERALS), "monitor-gamer_01.jpg", 7),

                // Audio y video
                p("Auriculares Gamer 7.1", "Sonido envolvente, micrófono retráctil", "169.00", categories.get(AUDIO), "auriculares_01.jpg", 22),
                p("Micrófono de Condensador USB", "Patrón cardioide, ideal para streaming", "219.00", categories.get(AUDIO), "microfono_01.jpg", 14),
                p("Cámara Web Full HD 1080p", "Enfoque automático, micrófono integrado", "129.00", categories.get(AUDIO), "camara-web_01.jpg", 20),

                // Accesorios
                p("Cargador Inalámbrico 15W", "Carga rápida compatible con Qi", "79.00", categories.get(PERIPHERALS), "cargador-inalambrico_01.jpg", 45),
                p("Estación de Carga Multipuerto", "6 puertos USB, carga inteligente", "99.00", categories.get(PERIPHERALS), "estacion-carga-multiport_01.jpg", 0), // sin stock a propósito
                p("Barra LED RGB para Escritorio", "Control por app, 16 millones de colores", "89.00", categories.get(PERIPHERALS), "barra-led_01.jpg", 28),

                // Oficina
                p("Silla Ergonómica Premium", "Soporte lumbar, reposabrazos 4D, malla transpirable", "899.00", categories.get(PERIPHERALS), "silla-ergonomica-premium_01.jpg", 11)
        );
    }

    private static Product p(String name, String description, String price,
                             UUID categoryId, String image, int stock) {
        return new Product(name, description, new BigDecimal(price),
                categoryId, "/images_small" +
                "/" + image, stock);
    }
}
