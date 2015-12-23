package br.edu.ifpb.servicoweb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ServicoServlet
 */
@WebServlet("/servicoservlet")
public class ServicoServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Tipo e codifica��o do conte�do de resposta.
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		PrintWriter pw = response.getWriter();
		pw.write("{'nome':'Kamila de Farias'}");
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		StringBuffer stringBuffer = new StringBuffer();
		String line = null;

		// Leitura do conte�do do pacote da requisi��o HTTP.
		BufferedReader reader = request.getReader();

		while ((line = reader.readLine()) != null) {
			stringBuffer.append(line);
		}

		// Convers�o para JSONObject
		try {
			JSONObject json = new JSONObject(stringBuffer.toString());

			String nome = json.getString("nome");
			String senha = json.getString("senha");

			// Tipo e codifica��o do conte�do de resposta.
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			// Conte�do de resposta.
			PrintWriter pw = response.getWriter();

			if (nome.toLowerCase().trim().equals("fulano")
					&& senha.trim().equals("123")) {
				// Caso verdadeiro.
				try {

					pw.write("{'key': '" + criptografarSha256("IFPB") + "'}");
					response.setStatus(200);

				} catch (NoSuchAlgorithmException e) {

					response.setStatus(500);
					pw.write("{'mensagem': 'Problema interno ao servidor.'}");
				}

			} else {

				// Caso falso.
				response.setStatus(500);
				pw.write("{'mensagem': 'Usu�rio ou senha incorretos.'}");
			}
			pw.close();
		} catch (JSONException je) {
			je.printStackTrace();
		}
	}

	public static String criptografarSha256(String valorPlano)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(valorPlano.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();

		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}

		String senha = hexString.toString();

		return senha;
	}
}