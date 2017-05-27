package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.algaworks.pedidovenda.model.StatusVuelo;
import com.algaworks.pedidovenda.model.Vuelo;
import com.algaworks.pedidovenda.repository.Vuelos;
import com.algaworks.pedidovenda.repository.filter.VueloFilter;

@Named
@ViewScoped
public class PesquisaVuelosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Vuelos vuelos;
	
	private VueloFilter filtro;
	private List<Vuelo> vuelosFiltrados;
	
	public PesquisaVuelosBean() {
		filtro = new VueloFilter();
		vuelosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		
		vuelosFiltrados = vuelos.filtrados(filtro);
	}
	
	public StatusVuelo[] getStatuses() {
		return StatusVuelo.values(); 
	}
	
	public List<Vuelo> getVuelosFiltrados() {
		return vuelosFiltrados;
	}

	public VueloFilter getFiltro() {
		return filtro;
	}
	
	public void posProcessarXls(Object documento) {
		HSSFWorkbook planilha = (HSSFWorkbook) documento;
		HSSFSheet folha = planilha.getSheetAt(0);
		HSSFRow cabecalho = folha.getRow(0);
		HSSFCellStyle estiloCelula = planilha.createCellStyle();
		Font fonteCabecalho = planilha.createFont();
		
		fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
		fonteCabecalho.setBold(true);
		fonteCabecalho.setFontHeightInPoints((short) 16);
		
		estiloCelula.setFont(fonteCabecalho);
		estiloCelula.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		for (int i = 0; i < cabecalho.getPhysicalNumberOfCells(); i++) {
			cabecalho.getCell(i).setCellStyle(estiloCelula);
		}
	}
	
}