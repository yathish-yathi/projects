package com.yathish.Order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.yathish.Order.entity.Sequence;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations mongoOperations;

    public Integer generateNextSequence(){
         Sequence nextSequence = mongoOperations.findAndModify(
            Query.query(Criteria.where("_id").is("order_sequence")),
            new Update().inc("sequenceValue", 1),
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            Sequence.class
        );

        if (nextSequence == null) {
            // Fallback: first value should be 1, but guard against unexpected null
            return 1;
        }
        return nextSequence.getSequenceValue();
    }

}
