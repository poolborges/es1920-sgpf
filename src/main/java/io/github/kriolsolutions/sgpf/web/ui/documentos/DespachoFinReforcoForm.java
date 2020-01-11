/*
 * Copyright 2019 kriolSolutions.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kriolsolutions.sgpf.web.ui.documentos;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import io.github.kriolsolutions.sgpf.backend.bal.dto.PedidoReforcoDto;
import io.github.kriolsolutions.sgpf.backend.bal.services.api.DespachoFinanciamentoReforcoAcoes;
import io.github.kriolsolutions.sgpf.backend.dal.entidades.projeto.Projeto;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pauloborges
 */
public class DespachoFinReforcoForm extends AbstractDespachoForm {

    private final BeanValidationBinder<PedidoReforcoDto> binder = new BeanValidationBinder<>(PedidoReforcoDto.class);

    private NumberField montanteReforco = new NumberField();
    private DatePicker dataPedido = new DatePicker();

    Button aprovarButton = new Button("Aprovar");
    Button rejeitarButton = new Button("Rejeitar");
    private final DespachoFinanciamentoReforcoAcoes despachoAccoes;
    private final PedidoReforcoDto despacho = new PedidoReforcoDto();

    public DespachoFinReforcoForm(DespachoFinanciamentoReforcoAcoes despachoAccoes, Projeto projeto) {

        this.despachoAccoes = despachoAccoes;
        this.despacho.setProjetoId(projeto.getId());
        setupFields();
    }

    @Override
    protected void setupFields() {
        montanteReforco.setLabel("Montante Requerido");
        binder.forMemberField(montanteReforco);
        this.add(montanteReforco);

        binder.bindInstanceFields(this);
        /* */
        // Button bar
        aprovarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        aprovarButton.addClickListener((event) -> {
            try {
                binder.writeBean(despacho);
                this.despachoAccoes.aprovar(despacho);
                AlertUtils.sucess("Submetido com Sucesso").open();
            } catch (ValidationException ex) {
                Logger.getLogger(DespachoFinReforcoForm.class.getName()).log(Level.SEVERE, null, ex);
                handleException(ex);

            }
        });

        rejeitarButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        rejeitarButton.addClickListener((event) -> {
            try {
                binder.writeBean(despacho);
                this.despachoAccoes.rejeitar(despacho);
                AlertUtils.sucess("Submetido com Sucesso").open();
            } catch (ValidationException ex) {
                Logger.getLogger(DespachoFinReforcoForm.class.getName()).log(Level.SEVERE, null, ex);
                handleException(ex);
            }
        });
        getActions().add(aprovarButton, rejeitarButton);
    }
}
