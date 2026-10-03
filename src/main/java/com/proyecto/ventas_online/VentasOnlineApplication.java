package com.proyecto.ventas_online;

import com.proyecto.ventas_online.infrastructure.ui.auth.LoginFrame;
import javax.swing.SwingUtilities;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class VentasOnlineApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(VentasOnlineApplication.class)
				.headless(false)
				.run(args);
                SwingUtilities.invokeLater(() -> 
                                ctx.getBean(LoginFrame.class).setVisible(true));
	}

}
