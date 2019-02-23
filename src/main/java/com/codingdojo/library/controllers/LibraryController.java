package com.codingdojo.library.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.library.models.Book;
import com.codingdojo.library.models.Issue;
import com.codingdojo.library.models.Student;
import com.codingdojo.library.services.LibraryService;
import com.codingdojo.library.validator.UserValidator;

@Controller
public class LibraryController {
	private final LibraryService libraryService;
	private final UserValidator userValidate;
	
	public LibraryController(LibraryService libraryService, UserValidator uv) {
		this.libraryService = libraryService;
		this.userValidate = uv;
	}
	
	//render Login and Registration page
	@RequestMapping("/")
	public String welcome(@ModelAttribute("user")Student student) {
		return "welcome.jsp";
	}
	
	//POST route for Registration
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user")Student student, BindingResult result, HttpSession session) {
		userValidate.validate(student, result);
		if(result.hasErrors()) {
			return "welcome.jsp";
		}else {
			Student rUser = libraryService.registerUser(student);
			session.setAttribute("userId", rUser.getLibID());
			return "redirect:/libdash";
		}
	}
	
	//POST route for Login
	@PostMapping("/login")
	public String login(@ModelAttribute("user")Student user, @RequestParam("email")String email,@RequestParam("password")String password, HttpSession session, Model model) {
		boolean auth = libraryService.authenticateUser(email, password);
		if(auth) {
			Student lUser = libraryService.findByEmail(email);
			session.setAttribute("userId", lUser.getLibID());
			return "redirect:/libdash";
		}else {
			model.addAttribute("error", "This login did not work. Please Try again!");
			return "welcome.jsp";
		}
	}
	
	//route to display library Dashboard
	@RequestMapping("/libdash")
		public String dashboard(@ModelAttribute("book")Book book, Model model, HttpSession session) {
		//display user name via session
		Long eUserId = (Long)session.getAttribute("userId");
		Student eUser = libraryService.findStudentById(eUserId);
		model.addAttribute("eUser", eUser);
		
		//display all books checked out by Student
		List<Issue> showbooks = libraryService.getAllIssuedBooks(eUser);
		model.addAttribute("showBooks", showbooks);
		return "dashboard.jsp";
	}
	
	//render Show all books page
	@RequestMapping("/books/all")
	public String allBooks(@ModelAttribute("book")Book book, Model model, HttpSession session) {
		//display user name via session
		Long eUserId = (Long)session.getAttribute("userId");
		Student eUser = libraryService.findStudentById(eUserId);
		model.addAttribute("eUser", eUser);
		
		//display all books in the db
		List<Book> showbooks = libraryService.getAllBooks();
		model.addAttribute("showbooks", showbooks);
		return "/books/allbooks.jsp";
	}
	//route to new page to enter new book
	@RequestMapping("/books/new")
	public String newBook(@ModelAttribute("book")Book book) {
		return "/books/new.jsp";
	}
	//Post route to create new book
	@RequestMapping(value= "/books", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "/books/new.jsp";
		}else {
			libraryService.createBook(book);
			return "redirect:/books/all";
		}
	}
	//route to issue selected book to student logged in
	@RequestMapping("books/{id}/issue")
	public String issuedBooks(@PathVariable("id") Long id, Model model, HttpSession session) {
		Book book = libraryService.findBook(id);
		Long eUserId = (Long)session.getAttribute("userId");
		Student eUser = libraryService.findStudentById(eUserId);
		model.addAttribute("eUser", eUser);
		//call service method to issue book to student
		libraryService.issueBook(eUser, book);
		return "redirect:/libdash";
	}
		
	//route to return book to the library
	@RequestMapping("/books/{id}")
	public String showBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		Book book = libraryService.findBook(id);
		Long eUserId = (Long)session.getAttribute("userId");
		Student eUser = libraryService.findStudentById(eUserId);
//		System.out.println("Returning the book placeholder.");
		libraryService.returnBook(book, eUser);
		return "redirect:/libdash";
	}
	//route to render the edit book page
//	@RequestMapping("/books/{id}/edit")
//	public String edit(@PathVariable("id") Long id, Model model) {
//	     Book book = libraryService.findBook(id);
//	     model.addAttribute("book", book);
//	     return "/books/edit.jsp";
//	}
	//route to update book
//	@RequestMapping(value="/books/{id}", method=RequestMethod.PUT)
//	public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
//	     if (result.hasErrors()) {
//	         return "/books/edit.jsp";
//	     } else {
//	         libraryService.updateBook(book);
//	         return "redirect:/books";
//	     }
//	 }
	//route to delete book
//	@RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
//    public String destroy(@PathVariable("id") Long id) {
//        libraryService.deleteBook(id);
//        return "redirect:/books";
//    }
	
	//log out
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
