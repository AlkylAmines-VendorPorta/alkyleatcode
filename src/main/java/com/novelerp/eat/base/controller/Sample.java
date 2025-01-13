package com.novelerp.eat.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.SampleDto;

@Controller
public class Sample {

	@RequestMapping(value="/sample",method=RequestMethod.GET)
	public ModelAndView sampleView(){
		ModelAndView view =  new ModelAndView("sample");
/*		view.addObject("sample", new SampleDto());*/
		return view;
	}
	
	@RequestMapping(value="/sampleMethod",method=RequestMethod.POST)
	public String sampleMethod(@ModelAttribute ("sample") SampleDto sampleObj){
		
		return "";
	}
	
	/*@RequestMapping(value="/sample1",method=RequestMethod.GET)
	public ModelAndView sampleView1(){
		ModelAndView view =  new ModelAndView("sample");
		view.addObject("sample1", new SampleDto());
		return view;
	}*/
	
	@RequestMapping(value="/sampleMethod1",method=RequestMethod.POST)
	public String sampleMethod1(@ModelAttribute ("sample1") SampleDto sampleObj){
		return "sampleSuccess";
	/*	return new ModelAndView("redirect:/sampleSuccess");*/
	}
	
	@RequestMapping(value="/sampleSuccess",method=RequestMethod.GET)
	public String sampleSuccess(){
		
		return "sample2";
	}

}
