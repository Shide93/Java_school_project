package com.tsystems.javaschool.webshop.controllers.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shide on 05.04.2016.
 */
@Controller
public class ErrorHandlerController {

    /**
     * The constant NOT_FOUND.
     */
    private static final String NOT_FOUND = "/notFound";
    /**
     * The constant NOT_FOUND_PAGE.
     */
    private static final String NOT_FOUND_PAGE = "/errorPages/notFound";
    /**
     * The constant FORBIDDEN.
     */
    private static final String FORBIDDEN = "/forbidden";
    /**
     * The constant FORBIDDEN_PAGE.
     */
    private static final String FORBIDDEN_PAGE = "/errorPages/forbidden";
    /**
     * The constant BAD_REQUEST.
     */
    private static final String BAD_REQUEST = "/badRequest";
    /**
     * The constant BAD_REQUEST_PAGE.
     */
    private static final String BAD_REQUEST_PAGE = "/errorPages/badRequest";
    /**
     * The constant SERVER_ERROR.
     */
    private static final String SERVER_ERROR = "/error";
    /**
     * The constant SERVER_ERROR_PAGE.
     */
    private static final String SERVER_ERROR_PAGE = "/errorPages/error";


    /**
     * Handle not found string.
     *
     * @return the string
     */
    @RequestMapping(value = NOT_FOUND)
    public final String handleNotFound() {

        return NOT_FOUND_PAGE;
    }

    /**
     * Handle forbidden string.
     *
     * @return the string
     */
    @RequestMapping(value = FORBIDDEN)
    public final String handleForbidden() {

        return FORBIDDEN_PAGE;
    }

    /**
     * Handle bad request string.
     *
     * @return the string
     */
    @RequestMapping(value = BAD_REQUEST)
    public final String handleBadRequest() {

        return BAD_REQUEST_PAGE;
    }

    /**
     * Handle server error string.
     *
     * @return the string
     */
    @RequestMapping(value = SERVER_ERROR)
    public final String handleServerError() {

        return SERVER_ERROR_PAGE;
    }
}
