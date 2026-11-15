package com.proyecto.ventas_online;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.proyecto.ventas_online.application.ports.out.SessionContext;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentResponse;
import com.proyecto.ventas_online.application.service.payment.PaymentGatewayRegistry;
import com.proyecto.ventas_online.domain.model.PaymentMethod;
import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.domain.model.Session;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.AuthController;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.AuthApi;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.dto.PayPalPaymentRequest;
import com.proyecto.ventas_online.infrastructure.ui.auth.LoginFrame;
import javax.swing.SwingUtilities;

import com.proyecto.ventas_online.infrastructure.ui.factory.provider.WindowFactoryProvider;
import com.proyecto.ventas_online.infrastructure.ui.session.SessionRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Scanner;

@SpringBootApplication
@ConfigurationPropertiesScan
public class VentasOnlineApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(VentasOnlineApplication.class)
				.headless(false)
				.run(args);

		SwingUtilities.invokeLater(() -> {
			FlatMacLightLaf.setup();
			SessionRouter sessionRouter = ctx.getBean(SessionRouter.class);
			sessionRouter.startAppSession();
		});
	}
}
