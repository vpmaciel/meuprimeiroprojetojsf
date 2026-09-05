package br.com.fileter;

import java.io.IOException;

import br.com.entidades.Pessoa;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" })
public class FilterAutenticacao implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {

	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();

	Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");

	String url = req.getServletPath();

	// Em projetos JSF modernos (Jakarta Faces), o redirecionamento costuma
	// apontar diretamente para a extensão física .xhtml
	if (!url.equalsIgnoreCase("/index.jsf") && usuarioLogado == null) {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
	    dispatcher.forward(request, response);
	    return;
	} else {
	    // executa as ações do request e do response
	    chain.doFilter(request, response);
	}
    }
}
