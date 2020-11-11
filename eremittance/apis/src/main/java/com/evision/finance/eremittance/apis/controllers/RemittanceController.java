package com.evision.finance.eremittance.apis.controllers;

import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
import com.evision.finance.eremittance.authenticator.entity.PageVO;
import com.evision.finance.eremittance.apis.service.RemittanceServiceImpl;
import com.evision.finance.eremittance.apis.view.PartnerVO;
import com.evision.finance.eremittance.apis.view.RemittanceVO;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/Remittance")
public class RemittanceController {

    private RemittanceServiceImpl service;

    public RemittanceController(RemittanceServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public RemittanceVO createRemittance(@Valid @RequestBody RemittanceVO remittance) {
        return service.createRemittance(remittance);
    }

    @PostMapping("/bulk")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void  createRemittanceBulk(@RequestBody List<RemittanceVO> remittances) {
         service.createRemittanceBulk(remittances);
    }


    @GetMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public RemittanceVO getRemittanceById(@Valid @PathVariable("id") Long id) {
//      return service.findRemittanceById(id);
        return new RemittanceVO().setId(545L);
        //return null;
    }

    @GetMapping(value ="/",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public PageVO<RemittanceVO> getAllRemittances(@RequestParam(required = false) Long partnerId,
                                                  @RequestParam(required = false) String senderName,
                                                  @RequestParam(required = false) String emailAddress,
                                                  @RequestParam(required = false) StatusRef status,
                                                  @RequestParam(required = false) Integer pageNumber,
                                                  @RequestParam(required = false) Integer pageSize,
                                                  @RequestParam(required = false) String[] sortBy,
                                                  @RequestParam(required = false) Sort.Direction order) {

        return service.findAllRemittances(pageNumber, pageSize, sortBy,
                                          order, new RemittanceVO().setPartner(PartnerVO.of().setId(partnerId))
                                                  .setEmailAddress(emailAddress)
                                                  .setStatus(status).setSenderName(senderName));
    }



    @PutMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public void  updateRemittance(@Valid @RequestBody RemittanceVO remittance) {
         service.updateRemittance(remittance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteRemittanceById(@Valid @PathVariable("id") Long id) {
         service.deleteRemittanceById(id);
    }

}
