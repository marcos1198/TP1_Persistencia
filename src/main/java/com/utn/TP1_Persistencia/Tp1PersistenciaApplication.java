package com.utn.TP1_Persistencia;

import com.utn.TP1_Persistencia.Enumeraciones.Estado;
import com.utn.TP1_Persistencia.Enumeraciones.FormaPago;
import com.utn.TP1_Persistencia.Enumeraciones.Tipo;
import com.utn.TP1_Persistencia.Enumeraciones.TipoEnvio;
import com.utn.TP1_Persistencia.entidades.*;
import com.utn.TP1_Persistencia.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import java.text.SimpleDateFormat;


@SpringBootApplication
public class Tp1PersistenciaApplication {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp1PersistenciaApplication.class, args);
		System.out.println("Estoy probando One to Many unidireccional");
	}

	//de cliente a domicilio
	@Bean
	CommandLineRunner initClienteDomicilio(ClienteRepository clienteRepo, DomicilioRepository domicilioRepo) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO--------");

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Calle 1")
					.numero("123")
					.localidad("bermejo")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Calle 2")
					.numero("456")
					.localidad("dorrego")
					.build();

			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("2615340268")
					.email("pepe@hotmail.es")
					.build();

			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);

			clienteRepository.save(cliente);

			Cliente clienteRecuperada = clienteRepository.findById(cliente.getId()).orElse(null);

			if (clienteRecuperada != null) {
				System.out.println("Nombre: " + clienteRecuperada.getNombre());
				System.out.println("Apellido: " + clienteRecuperada.getApellido());
				System.out.println("Telefono: " + clienteRecuperada.getTelefono());
				System.out.println("Email: " + clienteRecuperada.getEmail());

				clienteRecuperada.mostrarDomicilios();

			}

		};

	}

	//De cliente a Pedido
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	PedidoRepository pedidoRepository;

	@Bean
	CommandLineRunner initClientePedido(ClienteRepository clienteRepo, PedidoRepository pedidoRepo) {
		return args -> {

			Pedido pedido1 = Pedido.builder()
					.estado(Estado.INICIADO)
					.fecha(format.parse("12-09-2023"))
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(429.99)
					.build();

			Pedido pedido2 = Pedido.builder()
					.estado(Estado.PREPARACION)
					.fecha(format.parse("12-09-2023"))
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(469.99)
					.build();

			Pedido pedido3 = Pedido.builder()
					.estado(Estado.ENTREGADO)
					.fecha(format.parse("12-09-2023"))
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(459.99)
					.build();

			Pedido pedido4 = Pedido.builder()
					.estado(Estado.PREPARACION)
					.fecha(format.parse("10-09-2023"))
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(399.99)
					.build();

			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("2615340268")
					.email("pepe@hotmail.es")
					.build();

			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido2);
			cliente.agregarPedido(pedido3);
			cliente.agregarPedido(pedido4);

			clienteRepository.save(cliente);

			Cliente clienteRecuperada = clienteRepository.findById(cliente.getId()).orElse(null);

			if (clienteRecuperada != null) {
				System.out.println("Nombre: " + clienteRecuperada.getNombre());
				System.out.println("Apellido: " + clienteRecuperada.getApellido());
				System.out.println("Telefono: " + clienteRecuperada.getTelefono());
				System.out.println("Email: " + clienteRecuperada.getEmail());

				clienteRecuperada.mostrarDomicilios();

			}


		};

	}
	//de pedido a factura
	@Autowired
	FacturaRepository facturaRepository;

	@Bean
	CommandLineRunner initPedido(PedidoRepository pedidoRepo) {
		return args -> {

			Pedido pedido = Pedido.builder()
					.estado(Estado.PREPARACION)
					.fecha(format.parse("10-09-2023"))
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(399.99)
					.build();

			Factura factura = Factura.builder()
					.numero(10)
					.fecha(format.parse("10-09-2023"))
					.descuento(0.5)
					.formaPago(FormaPago.EFECTIVO)
					.total(500)
					.build();

			pedido.setFactura(factura);

			pedidoRepository.save(pedido);

			Pedido pedidoRecuperada = pedidoRepository.findById(pedido.getId()).orElse(null);
			if (pedidoRecuperada != null) {
				System.out.println("Estado: " + pedidoRecuperada.getEstado());
				System.out.println("Fecha: " + pedidoRecuperada.getFecha());
				System.out.println("TipoEnvio: " + pedidoRecuperada.getTipoEnvio());
				System.out.println("Total : " + pedidoRecuperada.getTotal());
				System.out.println("Numero : " + pedidoRecuperada.getFactura().getNumero());
				System.out.println("Fecha : " + pedidoRecuperada.getFactura().getFecha());
				System.out.println("Descuento : " + pedidoRecuperada.getFactura().getDescuento());
				System.out.println("FormaPago : " + pedidoRecuperada.getFactura().getFormaPago());
				System.out.println("Total : " + pedidoRecuperada.getFactura().getTotal());

			}
		};

	}

	//De pedido a detalle pedido
	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	@Bean
	CommandLineRunner initPedidoDetallePedido(PedidoRepository pedidoRepo, DetallePedidoRepository detallePedidoRepo) {
		return args -> {

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(349.99)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(249.99)
					.build();

			Pedido pedido = Pedido.builder()
					.estado(Estado.ENTREGADO)
					.fecha(format.parse("10-09-2023"))
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(799.99)
					.build();

			pedido.agregarDetallePedido(detallePedido1);
			pedido.agregarDetallePedido(detallePedido2);

			pedidoRepository.save(pedido);

			Pedido pedidoRecuperada = pedidoRepository.findById(pedido.getId()).orElse(null);

			if (pedidoRecuperada != null) {
				System.out.println("Estado: " + pedidoRecuperada.getEstado());
				System.out.println("Fecha: " + pedidoRecuperada.getFecha());
				System.out.println("TipoEnvio: " + pedidoRecuperada.getTipoEnvio());
				System.out.println("Total: " + pedidoRecuperada.getTotal());

				pedidoRecuperada.mostrarDetallePedidos();

			}

		};

	}

	//De Rubro a Producto
	@Autowired
	RubroRepository rubroRepository;
	@Bean
	CommandLineRunner initRubroProducto(RubroRepository rubroRepo, ProductoRepository productoRepo) {
		return args -> {


			Producto producto1 = Producto.builder()
					.tipo(Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(15)
					.denominacion("Papas Fritas")
					.precioVenta(777)
					.precioCompra(550)
					.stockActual(120)
					.stockMinimo(10)
					.unidadMedida("2")
					.receta("receta Lomo: ....")
					.build();
			Producto producto2 = Producto.builder()
					.tipo(Tipo.INSUMO)
					.tiempoEstimadoCocina(10)
					.denominacion("Papas")
					.precioVenta(500)
					.precioCompra(350)
					.stockActual(250)
					.stockMinimo(100)
					.unidadMedida("10")
					.receta("receta: ....")
					.build();

			Rubro rubro = Rubro.builder()
					.denominacion("Comida")
					.build();

			rubro.agregarProducto(producto1);
			rubro.agregarProducto(producto2);

			rubroRepository.save(rubro);

			Rubro rubroRecuperada = rubroRepository.findById(rubro.getId()).orElse(null);
			if (rubroRecuperada != null) {
				System.out.println("-Mostrar productos dentro de un rubro-");
				System.out.println("Rubro: " + rubroRecuperada.getDenominacion());
				rubroRecuperada.mostrarProductos();
			}

			};

	}
}
