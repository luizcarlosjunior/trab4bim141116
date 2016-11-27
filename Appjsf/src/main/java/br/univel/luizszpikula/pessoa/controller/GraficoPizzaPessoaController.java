package br.univel.luizszpikula.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.univel.luizszpikula.repository.PessoaRepository;

/**
* Controlador para gerar gráfico de pizza com base nos dados de pessoas
* @author Luiz Carlos Szpikula Junior
*/

@Named(value="graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;


	private PieChartModel pieChartModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public  void init(){
		this.pieChartModel = new PieChartModel();
		this.MontaGrafico();
	}

	/***
	 * MONTA O GRÁFICO NA PÁGINA
	 */
	private void MontaGrafico(){

		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa(); // CONSULTA OS DADOS PARA MONTAR O GRÁFICO

		hashtableRegistros.forEach((chave,valor) -> {
			pieChartModel.set(chave, valor); // INFORMANDO OS VALORES PARA MONTAR O GRÁFICO
		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo"); // título do gráfico
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");

	}
}