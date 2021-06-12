package com.ducterry.base.ducterrybase.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDTO {
	private int page;
	private int limit;
	private Long total;
}
