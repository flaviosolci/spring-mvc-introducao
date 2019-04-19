package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void iniBinder(final WebDataBinder dataBinder) {
		dataBinder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(final Produto produto) {
		final ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		modelAndView.setStatus(HttpStatus.OK);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(final MultipartFile sumario, @Valid final Produto produto, final BindingResult result, final RedirectAttributes redirectAttributes) {
		System.out.println("Produto -> " + produto);
		System.out.println("Sumario -> " + sumario.getOriginalFilename());

		if (result.hasErrors()) {
			return form(produto);
		}
		final String path = fileSaver.write("arquivo-sumarios", sumario);
		produto.setSumarioPath(path);
		produtoDao.gravar(produto);

		redirectAttributes.addFlashAttribute("sucesso", "Produto Cadastrado com sucesso");

		return new ModelAndView("redirect:/produtos");

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		final List<Produto> prodList = produtoDao.listar();

		final ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", prodList);
		modelAndView.setStatus(HttpStatus.OK);
		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") final Integer id) {
		final ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		final Produto produto = produtoDao.findById(id);
		modelAndView.setStatus(HttpStatus.OK);
		modelAndView.addObject("produto", produto);

		return modelAndView;

	}

}
