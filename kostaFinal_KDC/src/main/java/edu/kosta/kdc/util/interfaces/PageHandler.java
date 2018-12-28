package edu.kosta.kdc.util.interfaces;

import edu.kosta.kdc.model.dto.PageDTO;

public interface PageHandler {
    
    PageDTO pageInfoSet(int setPage, int setCountList, int setCountPage, int setTotalCount);

}
