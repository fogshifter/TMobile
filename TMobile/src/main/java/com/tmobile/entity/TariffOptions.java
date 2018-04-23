package com.tmobile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tariff_options")
public class TariffOptions implements Serializable {
    private Tariff tariff;

    private Option option;

    public TariffOptions() {}

    public TariffOptions(Tariff t, Option o) {
        this.tariff = t;
        this.option = o;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "tariff_id")
    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "option_id")
    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TariffOptions)) return false;
        TariffOptions that = (TariffOptions) o;
        return Objects.equals(tariff, that.tariff) &&
                Objects.equals(option, that.option);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tariff, option);
    }
}
