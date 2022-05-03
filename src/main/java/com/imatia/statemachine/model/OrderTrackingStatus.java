package com.imatia.statemachine.model;

public enum OrderTrackingStatus {
	ESTADO_INCORRECTO, RECOGIDO_EN_ALMACÉN, EN_REPARTO, INCIDENCIA_EN_ENTREGA, ENTREGADO;

	public static OrderTrackingStatus valueOf(int trackingStatusId) {
		OrderTrackingStatus status = ESTADO_INCORRECTO;
		OrderTrackingStatus[] values = OrderTrackingStatus.values();
		int i = 0;
		while (i < values.length && status == ESTADO_INCORRECTO) {
			if (values[i].ordinal() == trackingStatusId) {
				status = values[i];
			}
			i++;
		}
		return status;
	}

}
