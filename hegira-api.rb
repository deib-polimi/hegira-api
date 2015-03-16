#
# 
#
# Generated by <a href="http://enunciate.codehaus.org">Enunciate</a>.
#
require 'json'

# adding necessary json serialization methods to standard classes.
class Object
  def to_jaxb_json_hash
    return self
  end
  def self.from_json o
    return o
  end
end

class String
  def self.from_json o
    return o
  end
end

class Boolean
  def self.from_json o
    return o
  end
end

class Numeric
  def self.from_json o
    return o
  end
end

class Time
  #json time is represented as number of milliseconds since epoch
  def to_jaxb_json_hash
    return (to_i * 1000) + (usec / 1000)
  end
  def self.from_json o
    if o.nil?
      return nil
    else
      return Time.at(o / 1000, (o % 1000) * 1000)
    end
  end
end

class Array
  def to_jaxb_json_hash
    a = Array.new
    each { | _item | a.push _item.to_jaxb_json_hash }
    return a
  end
end

class Hash
  def to_jaxb_json_hash
    h = Hash.new
    each { | _key, _value | h[_key.to_jaxb_json_hash] = _value.to_jaxb_json_hash }
    return h
  end
end


module It

module Polimi

module Hegira

module Api

  # 
  class ZKobject 

    # The lower extreme of the generated range of unique ids.
    attr_accessor :lowExtreme
    # The highest extreme of the generated range of unique ids.
    attr_accessor :highExtreme
    # The generated unique id.
    attr_accessor :id
    # The status given in response to the REST request.
    attr_accessor :requestStatus

    # the json hash for this ZKobject
    def to_jaxb_json_hash
      _h = {}
      _h['lowExtreme'] = lowExtreme.to_jaxb_json_hash unless lowExtreme.nil?
      _h['highExtreme'] = highExtreme.to_jaxb_json_hash unless highExtreme.nil?
      _h['id'] = id.to_jaxb_json_hash unless id.nil?
      _h['requestStatus'] = requestStatus.to_jaxb_json_hash unless requestStatus.nil?
      return _h
    end

    # the json (string form) for this ZKobject
    def to_json
      to_jaxb_json_hash.to_json
    end

    #initializes this ZKobject with a json hash
    def init_jaxb_json_hash(_o)
      @lowExtreme = String.from_json(_o['lowExtreme']) unless _o['lowExtreme'].nil?
      @highExtreme = String.from_json(_o['highExtreme']) unless _o['highExtreme'].nil?
      @id = String.from_json(_o['id']) unless _o['id'].nil?
      @requestStatus = It::Polimi::Hegira::Api::Status.from_json(_o['requestStatus']) unless _o['requestStatus'].nil?
    end

    # constructs a ZKobject from a (parsed) JSON hash
    def self.from_json(o)
      if o.nil?
        return nil
      else
        inst = new
        inst.init_jaxb_json_hash o
        return inst
      end
    end
  end

end

end

end

end

module It

module Polimi

module Hegira

module Api

  # 
  class Status 

    # OK, WARNING or ERROR.
    attr_accessor :State
    # 
    attr_accessor :message
    # 
    attr_accessor :error_code

    # the json hash for this Status
    def to_jaxb_json_hash
      _h = {}
      _h['status'] = State.to_jaxb_json_hash unless State.nil?
      _h['message'] = message.to_jaxb_json_hash unless message.nil?
      _h['error_code'] = error_code.to_jaxb_json_hash unless error_code.nil?
      return _h
    end

    # the json (string form) for this Status
    def to_json
      to_jaxb_json_hash.to_json
    end

    #initializes this Status with a json hash
    def init_jaxb_json_hash(_o)
      @State = String.from_json(_o['status']) unless _o['status'].nil?
      @message = String.from_json(_o['message']) unless _o['message'].nil?
      @error_code = String.from_json(_o['error_code']) unless _o['error_code'].nil?
    end

    # constructs a Status from a (parsed) JSON hash
    def self.from_json(o)
      if o.nil?
        return nil
      else
        inst = new
        inst.init_jaxb_json_hash o
        return inst
      end
    end
  end

end

end

end

end