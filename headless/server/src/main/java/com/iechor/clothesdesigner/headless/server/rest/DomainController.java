package com.iechor.clothesdesigner.headless.server.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iechor.clothesdesigner.auth.api.authentication.pojo.User;
import com.iechor.clothesdesigner.auth.api.authentication.utils.UserHolder;
import com.iechor.clothesdesigner.headless.api.pojo.request.DomainReq;
import com.iechor.clothesdesigner.headless.api.pojo.request.DomainUpdateReq;
import com.iechor.clothesdesigner.headless.api.pojo.response.DomainResp;
import com.iechor.clothesdesigner.headless.server.service.DomainService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/semantic/domain")
public class DomainController {

    private DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping("/createDomain")
    public DomainResp createDomain(
            @RequestBody DomainReq domainReq,
            HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserHolder.findUser(request, response);
        return domainService.createDomain(domainReq, user);
    }

    @PostMapping("/updateDomain")
    public DomainResp updateDomain(
            @RequestBody DomainUpdateReq domainUpdateReq,
            HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserHolder.findUser(request, response);
        return domainService.updateDomain(domainUpdateReq, user);
    }

    @DeleteMapping("/deleteDomain/{domainId}")
    public Boolean deleteDomain(@PathVariable("domainId") Long domainId) {
        domainService.deleteDomain(domainId);
        return true;
    }

    @GetMapping("/getDomainList")
    public List<DomainResp> getDomainList(
            HttpServletRequest request, HttpServletResponse response) {
        User user = UserHolder.findUser(request, response);
        return domainService.getDomainListWithAdminAuth(user);
    }

    @GetMapping("/getDomain/{id}")
    public DomainResp getDomain(@PathVariable("id") Long id) {
        return domainService.getDomain(id);
    }

    @GetMapping("/getDomainListByIds/{domainIds}")
    public List<DomainResp> getDomainListByIds(@PathVariable("domainIds") String domainIds) {
        return domainService.getDomainList(
                Arrays.stream(domainIds.split(","))
                        .map(Long::parseLong)
                        .collect(Collectors.toList()));
    }
}
