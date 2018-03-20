package com.almundo.callcenter.util.chain.core;

import org.springframework.stereotype.Component;

import com.almundo.callcenter.util.chain.core.impl.ChainImpl;
import com.almundo.callcenter.util.chain.core.impl.HandlerImpl;

/**
 * @author axel.flores
 *
 * @param <T> - Object to proccess by the chain.
 */
@Component
public class ChainBuilder<T> {

    /**
     * Represent ever the first element of the chain.
     */
    private HandlerImpl<T> firstDelegateHandler;

    /**
     * @param firstDelegate - Logic Delegate for first element of the chain.
     * 
     * @return SuccessorBuilder.
     */
    public SuccessorBuilder first(Handler<T> firstDelegate) {
        this.firstDelegateHandler = new HandlerImpl<>(firstDelegate);
        return new SuccessorBuilder(this.firstDelegateHandler);
    }

    /**
     * Contiene la logica para definir la logica y encadenar los sucesores de la cadena.
     * 
     * @author axel.flores
     */
    public class SuccessorBuilder {
    	
        /**
         * Current Handler.
         */
        private HandlerImpl<T> currentHandler;

        /**
         * @param elemento actual previo a definir el sucesor en la cadena.
         */
        private SuccessorBuilder(HandlerImpl<T> firstDelegateHandler) {
            this.currentHandler = firstDelegateHandler;
        }

        /**
         * @param successorDelegate - logica sucesora.
         * 
         * @return SuccessorBuilder
         */
        public SuccessorBuilder successor(Handler<T> successorDelegate) {
            HandlerImpl<T> successorDelegateHandler = new HandlerImpl<>(successorDelegate);
            this.currentHandler.setSuccessor(successorDelegateHandler);
            this.currentHandler = successorDelegateHandler;
            return this;
        }

        /**
         * @return Chain<T> - cadena de responsabilidad.
         */
        public Chain<T> build() {
            return new ChainImpl<T>(firstDelegateHandler);
        }
    }
}