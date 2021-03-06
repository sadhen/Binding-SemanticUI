package com.sadhen.binding.component.dataentry

import org.scalajs.dom.raw.Event
import com.thoughtworks.binding.Binding.{Var, Vars}

import com.sadhen.binding.component.ComponentBuilder
import com.sadhen.binding.magic.ant

/**
  * Created by rendong on 17/1/23.
  */
class AutoCompleteBuilder extends ComponentBuilder[AutoCompleteBuilder] {
  var dataSource: Var[Seq[String]] = Var(Seq.empty)
  var placeholder: Var[String] = Var("")

  val filter: Var[String] = Var("")

  @ant
  override def build = {
    <div class="ui search">
      <div class="ui input">
        <input id="input"
               type="text"
               oninput={ event: Event =>
                 // filter := `input`.value
               }
               placeholder={ placeholder.bind } />
      </div>
      <div class="results"
           style={
             if (filter.bind.nonEmpty && dataSource.bind.exists(_.contains(filter.get)) )
               "display: block !important;"
             else
               ""
           }>
        { for (result <- Vars(dataSource.bind: _*) if filter.bind.nonEmpty && result.contains(filter.bind)) yield
          <a class="result">{ result }</a>
        }
      </div>
    </div>
  }
}
